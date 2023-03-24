use egui::{
    plot::{Bar, PlotUi},
    Color32,
};

use crate::bogo_sort::BogoSort;
use crate::bubble_sort::BubbleSort;
use crate::insertion_sort::InsertionSort;
use crate::merge_sort::MergeSort;

#[derive(Debug, Clone, Copy, PartialEq)]
pub enum SortType {
    BubbleSort,
    BogoSort,
    InsertionSort,
    MergeSort,
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
            SortType::MergeSort => Box::new(MergeSort::<T>::default()),
        }
    }
}

pub(crate) trait Sorter {
    type ElementType: Clone + Ord;

    fn init(&mut self, v: Vec<Self::ElementType>);
    fn next(&mut self);

    fn visualize(&mut self, ui: &mut PlotUi, convert: fn(Self::ElementType) -> f64);
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
