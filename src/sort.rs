use egui::{
    plot::{self, Bar, PlotUi},
    Color32,
};

use rand::{seq::SliceRandom, thread_rng};

use crate::bubble_sort::BubbleSort;
use crate::insertion_sort::InsertionSort;

#[derive(Debug, Clone, Copy, PartialEq)]
pub enum SortType {
    BubbleSort,
    BogoSort,
    InsertionSort,
}

impl SortType {
    pub(crate) fn sorter_from<T>(&self) -> Box<dyn Sorter<ElementType = T>>
    where
        T: Default + Ord + Clone + Copy + 'static,
    {
        match self {
            SortType::BubbleSort => Box::new(BubbleSort::<T>::default()),
            SortType::BogoSort => Box::new(BogoSort::<T>::default()),
            SortType::InsertionSort => Box::new(InsertionSort::<T>::default()),
        }
    }
}

pub(crate) trait Sorter {
    type ElementType: Clone + Ord;

    fn init(&mut self, v: Vec<Self::ElementType>);
    fn next(&mut self);

    fn visualize(&mut self, ui: &mut PlotUi, convert: fn(Self::ElementType) -> f64);
}

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

pub(crate) fn color(v: &mut Vec<Bar>, special: Vec<usize>, base: Color32, highlight: Color32) {
    for mut bar in v {
        let arg = bar.argument as usize;
        bar.fill = if special.contains(&arg) {
            highlight
        } else {
            base
        };
    }
}
