use egui::plot::{self, Bar, PlotUi};
use rand::{seq::SliceRandom, thread_rng};

use crate::sort::Sorter;

#[derive(Default)]
pub struct BogoSort<T> {
    current_vector: Vec<T>,
}

impl<T> Sorter for BogoSort<T>
where
    T: Ord + Clone + Copy,
{
    type ElementType = T;

    fn next(&mut self) {
        if self.current_vector.windows(2).all(|w| w[0] <= w[1]) {
            return;
        }
        self.current_vector.shuffle(&mut thread_rng());
    }

    fn init(&mut self, v: Vec<T>) {
        self.current_vector = v;
    }

    fn visualize(&mut self, ui: &mut PlotUi, convert: fn(Self::ElementType) -> f64) {
        let v = self
            .current_vector
            .iter()
            .enumerate()
            .map(|(x, y)| Bar::new(x as f64 + 0.25, convert(*y) as f64))
            .collect();

        let chart = plot::BarChart::new(v);

        ui.bar_chart(chart);
    }
}
