use egui::{
    plot::{self, Bar, PlotUi},
    Color32,
};

use crate::sort::{color, Sorter};

#[derive(Default)]
pub struct BubbleSort<T> {
    current_vector: Vec<T>,
    index: usize,
    ceiling: usize,
}

impl<T> Sorter for BubbleSort<T>
where
    T: Clone + Copy + Ord,
{
    type ElementType = T;

    fn next(&mut self) {
        if self.ceiling == 0 {
            return;
        }
        let mut vec = self.current_vector.clone();
        let index = self.index;
        if index < self.ceiling && vec[index] > vec[index + 1] {
            vec.swap(index, index + 1)
        }

        self.index += 1;

        if self.index >= self.ceiling {
            self.ceiling -= 1;
            self.index = 0;
        }
        self.current_vector = vec;
    }

    fn init(&mut self, v: Vec<T>) {
        self.ceiling = v.len() - 1;
        self.current_vector = v;
    }

    fn visualize(&mut self, ui: &mut PlotUi, convert: fn(Self::ElementType) -> f64) {
        let mut v = self
            .current_vector
            .iter()
            .enumerate()
            .map(|(x, y)| Bar::new(x as f64 + 0.25, convert(*y) as f64))
            .collect();

        color(
            &mut v,
            vec![self.index, self.index + 1],
            Color32::DARK_GRAY,
            Color32::WHITE,
        );

        let chart = plot::BarChart::new(v);

        ui.bar_chart(chart);
    }
}
