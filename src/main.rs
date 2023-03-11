use eframe::egui;
use egui::plot;
use rand::seq::SliceRandom;
use rand::thread_rng;
use sort::*;

pub mod sort;

struct MyApp {
    sort_type: SortType,
    my_sorter: Box<dyn sort::Sorter<ElementType = i64>>,
    step: u64,
    paused: bool,
}

impl MyApp {
    fn new() -> Self {
        let mut x = Self {
            sort_type: SortType::BubbleSort,
            my_sorter: SortType::sorter_from(&SortType::BubbleSort),
            step: 0,
            paused: false,
        };
        let mut vec: Vec<i64> = (0..100).collect();
        vec.shuffle(&mut thread_rng());
        x.my_sorter.init(vec);
        return x;
    }
}

impl eframe::App for MyApp {
    fn update(&mut self, ctx: &egui::Context, _frame: &mut eframe::Frame) {
        ctx.request_repaint();
        let mut sorter = self.sort_type;

        egui::CentralPanel::default().show(ctx, |ui| {
            egui::ComboBox::from_label("Sort type")
                .selected_text(format!("{:?}", sorter))
                .show_ui(ui, |ui_drop| {
                    ui_drop.selectable_value(&mut sorter, SortType::BubbleSort, "BubbleSort");
                    ui_drop.selectable_value(&mut sorter, SortType::BogoSort, "BogoSort");
                    ui_drop.selectable_value(&mut sorter, SortType::InsertionSort, "Insertion Sort")
                });
            egui::ComboBox::from_label("Pause")
                .selected_text(format!("{:?}", self.paused))
                .show_ui(ui, |ui_drop| {
                    ui_drop.selectable_value(&mut self.paused, false, "Play");
                    ui_drop.selectable_value(&mut self.paused, true, "Pause");
                });

            if sorter != self.sort_type {
                self.sort_type = sorter;
                self.my_sorter = self.sort_type.sorter_from();
                let mut vec: Vec<i64> = (0..100).collect();
                vec.shuffle(&mut thread_rng());
                self.my_sorter.init(vec)
            }

            if !self.paused {
                self.my_sorter.next();
                self.step += 1;
            }

            let plot = plot::Plot::new("BarChart")
                .allow_zoom(false)
                .allow_drag(false)
                .allow_scroll(false);

            plot.show(ui, |plot_ui| self.my_sorter.visualize(plot_ui, convert));
        });

        ctx.input(|i| {
            if self.paused && i.key_pressed(egui::Key::N) {
                self.my_sorter.next();
            } else if i.key_pressed(egui::Key::Space) {
                self.paused = !self.paused;
            }
        });
    }
}

fn convert(x: i64) -> f64 {
    x as f64
}

fn main() -> Result<(), eframe::Error> {
    let options = eframe::NativeOptions {
        initial_window_size: Some(egui::vec2(1280.0, 720.0)),
        vsync: false,
        ..Default::default()
    };
    eframe::run_native(
        "Sort Visualizer",
        options,
        Box::new(|_cc| Box::new(MyApp::new())),
    )
}
