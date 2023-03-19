use egui::{
    plot::{self, Bar, PlotUi},
    Color32,
};

use crate::sort::{color, Sorter};

#[derive(Default)]
pub struct InsertionSort<T> {
    current_vector: Vec<T>,
    index: usize,
}

impl<T> InsertionSort<T>
where
    T: Ord,
{
    fn binary_search(&self, begin: usize, end: usize, item: &T) -> usize {
        if begin >= end {
            return if item > &self.current_vector[begin] {
                begin + 1
            } else {
                begin
            };
        }

        let mid = (begin + end) / 2;

        if item > &self.current_vector[mid] {
            return self.binary_search(mid + 1, end, item);
        }

        if item < &self.current_vector[mid] {
            return self.binary_search(begin, if mid > 0 { mid - 1 } else { 0 }, item);
        }

        mid + 1
    }
}

impl<T> Sorter for InsertionSort<T>
where
    T: Ord + Clone + Copy,
{
    type ElementType = T;

    fn next(&mut self) {
        if self.index >= self.current_vector.len() {
            return;
        }
        let val = self.current_vector[self.index].clone();
        let i = self.binary_search(0, self.index, &val);
        self.current_vector.insert(i, val);
        self.current_vector.remove(self.index + 1);
        self.index += 1;
    }

    fn init(&mut self, v: Vec<Self::ElementType>) {
        self.current_vector = v;
    }

    fn visualize(&mut self, ui: &mut PlotUi, convert: fn(Self::ElementType) -> f64) {
        let mut v = self
            .current_vector
            .iter()
            .enumerate()
            .map(|(x, y)| Bar::new(x as f64 + 0.25, convert(*y) as f64))
            .collect();

        color(&mut v, vec![self.index], Color32::DARK_GRAY, Color32::WHITE);

        let chart = plot::BarChart::new(v);

        ui.bar_chart(chart);
    }
}
