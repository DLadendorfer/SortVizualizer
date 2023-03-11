use egui::{
    plot::{self, Bar, PlotUi},
    Color32,
};

use rand::{seq::SliceRandom, thread_rng};

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
pub struct BubbleSort<T> {
    current_vector: Vec<T>,
    index: usize,
    ceiling: usize,
}

#[derive(Default)]
pub struct BogoSort<T> {
    current_vector: Vec<T>,
}

#[derive(Default)]
pub struct InsertionSort<T> {
    current_vector: Vec<T>,
    index: usize,
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
        if self.index >= self.current_vector.len() - 1 {
            return;
        }
        let val = self.current_vector[self.index].clone();
        let i = self.binary_search(0, self.index, &val);
        self.current_vector.insert(i, val);
        self.current_vector.remove(self.index);
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

fn color(v: &mut Vec<Bar>, special: Vec<usize>, base: Color32, highlight: Color32) {
    for mut bar in v {
        let arg = bar.argument as usize;
        bar.fill = if special.contains(&arg) {
            highlight
        } else {
            base
        };
    }
}
