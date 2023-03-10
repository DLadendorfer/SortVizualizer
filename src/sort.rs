use egui::{plot::Bar, Color32};
use rand::{seq::SliceRandom, thread_rng};

#[derive(Debug, Clone, Copy, PartialEq)]
pub enum SortType {
    BubbleSort,
    BogoSort,
}

impl SortType {
    pub(crate) fn sorter_from<T>(&self) -> Box<dyn Sorter<ElementType = T>>
    where
        T: Default + Ord + Clone + 'static,
    {
        match self {
            SortType::BubbleSort => Box::new(BubbleSort::<T>::default()),
            SortType::BogoSort => Box::new(BogoSort::<T>::default()),
        }
    }
}

pub(crate) trait Sorter {
    type ElementType: Clone + Ord;

    fn init(&mut self, v: Vec<Self::ElementType>);
    fn next(&mut self);
    fn get_vec(&self) -> &Vec<Self::ElementType>;
    fn color(&mut self, v: &Vec<Bar>) -> Vec<Bar>;
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

impl<T> Sorter for BubbleSort<T>
where
    T: Clone + Ord,
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

    fn get_vec(&self) -> &Vec<T> {
        &self.current_vector
    }

    fn init(&mut self, v: Vec<T>) {
        self.ceiling = v.len() - 1;
        self.current_vector = v;
    }

    fn color(&mut self, v: &Vec<Bar>) -> Vec<Bar> {
        let mut return_vec = Vec::<Bar>::new();
        for i in 0..v.len() {
            let mut bar = Bar::new(v[i].argument, v[i].value);

            bar.fill = if i == self.index || i == self.index + 1 {
                Color32::WHITE
            } else {
                Color32::DARK_GRAY
            };
            return_vec.push(bar);
        }
        return_vec
    }
}

impl<T> Sorter for BogoSort<T>
where
    T: Ord + Clone,
{
    type ElementType = T;

    fn next(&mut self) {
        if self.current_vector.windows(2).all(|w| w[0] <= w[1]) {
            return;
        }
        self.current_vector.shuffle(&mut thread_rng());
    }

    fn get_vec(&self) -> &Vec<T> {
        &self.current_vector
    }

    fn init(&mut self, v: Vec<T>) {
        self.current_vector = v;
    }

    fn color(&mut self, v: &Vec<Bar>) -> Vec<Bar> {
        v.clone()
    }
}
