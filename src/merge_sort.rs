use crate::sort::{color, color_highlight, Sorter};
use egui::{
    plot::{self, Bar, PlotUi},
    Color32,
};

#[derive(Default)]
pub struct MergeSort<T> {
    current_vector: Vec<T>,
    block_size: usize,
    index: usize,
}

fn merge<T>(vec: &mut Vec<T>, index: usize, block_size: usize)
where
    T: PartialOrd + Copy,
{
    let mut left = Vec::<T>::with_capacity(block_size);
    let mut right = Vec::<T>::with_capacity(block_size);

    for i in 0..block_size {
        left.push(vec[index + i]);
        right.push(vec[index + block_size + i])
    }

    let mut left_index: usize = 0;
    let mut right_index: usize = 0;
    let mut i = index;

    while left_index < left.len() && right_index < right.len() {
        if left[left_index] <= right[right_index] {
            vec[i] = left[left_index];
            left_index += 1;
        } else {
            vec[i] = right[right_index];
            right_index += 1;
        }
        i += 1;
    }
    while left_index < left.len() {
        vec[i] = left[left_index];
        i += 1;
        left_index += 1;
    }
    while right_index < right.len() {
        vec[i] = right[right_index];
        i += 1;
        right_index += 1;
    }
}

impl<T> Sorter for MergeSort<T>
where
    T: Ord + Clone + Copy,
{
    type ElementType = T;

    fn next(&mut self) {
        if self.block_size > self.current_vector.len() / 2 {
            return;
        }
        merge(&mut self.current_vector, self.index, self.block_size);
        self.index += self.block_size * 2;
        if self.index >= self.current_vector.len() {
            self.index = 0;
            self.block_size *= 2;
        }
    }

    fn init(&mut self, v: Vec<Self::ElementType>) {
        self.current_vector = v;
        self.block_size = 1;
        self.index = 0;
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
            (self.index..self.index + self.block_size).collect(),
            Color32::DARK_GRAY,
            Color32::RED,
        );

        color_highlight(
            &mut v,
            (self.index + self.block_size..self.index + 2 * self.block_size).collect(),
            Color32::GREEN,
        );
        let chart = plot::BarChart::new(v);

        ui.bar_chart(chart);
    }
}
