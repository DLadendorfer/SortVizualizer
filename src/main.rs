use eframe::egui;
use egui::{plot::Bar, widgets::*};
use rand::seq::SliceRandom;
use rand::thread_rng;
use sort::*;

pub mod sort;

struct MyApp {
    sort_type: SortType,
    my_sorter: Box<dyn sort::Sorter<ElementType = i64>>,
    step: u64,
}

impl MyApp {
    fn new() -> Self {
        let mut x = Self {
            sort_type: SortType::BubbleSort,
            my_sorter: SortType::sorter_from(&SortType::BubbleSort),
            step: 0,
        };
        let mut vec: Vec<i64> = (0..100).collect();
        vec.shuffle(&mut thread_rng());
        x.my_sorter.init(vec);
        return x;
    }
}

impl eframe::App for MyApp {
    fn update(&mut self, ctx: &egui::Context, _frame: &mut eframe::Frame) {
        self.step += 1;

        self.my_sorter.next();
        let mut v = self
            .my_sorter
            .get_vec()
            .iter()
            .enumerate()
            .map(|(x, y)| Bar::new(x as f64 + 0.25, *y as f64))
            .collect();

        v = self.my_sorter.color(&v).clone();

        let chart = plot::BarChart::new(v);

        let mut sorter = self.sort_type;

        egui::CentralPanel::default().show(ctx, |ui| {
            egui::ComboBox::from_label("Sort type:")
                .selected_text(format!("{:?}", sorter))
                .show_ui(ui, |ui_drop| {
                    ui_drop.selectable_value(&mut sorter, SortType::BubbleSort, "BubbleSort");
                    ui_drop.selectable_value(&mut sorter, SortType::BogoSort, "BogoSort");
                });

            if sorter != self.sort_type {
                self.sort_type = sorter;
                self.my_sorter = self.sort_type.sorter_from();
                let mut vec: Vec<i64> = (0..100).collect();
                vec.shuffle(&mut thread_rng());
                self.my_sorter.init(vec)
            }

            let plot = plot::Plot::new("BarChart")
                .allow_zoom(false)
                .allow_drag(false)
                .allow_scroll(false);
            plot.show(ui, |plot_ui| plot_ui.bar_chart(chart));

            ui.ctx().request_repaint(); // Makes the screen repaint
        });
    }
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
