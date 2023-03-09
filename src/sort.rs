use egui::{plot::Bar, Color32};

#[derive(Debug, PartialEq)]
pub enum SortType {
    BubbleSort,
}

pub(crate) trait Sorter: Iterator {
    type ElementType: Clone + Ord;

    fn init(&mut self, v: Vec<Self::ElementType>);
    fn color(&mut self, v: &Vec<Bar>) -> Vec<Bar>;
}

#[derive(Default)]
pub struct BubbleSort<T> {
    current_vector: Vec<T>,
    index: usize,
    ceiling: usize,
}

impl<T> Iterator for BubbleSort<T>
where
    T: Clone + Ord,
{
    type Item = Vec<T>;

    fn next(&mut self) -> Option<Vec<T>> {
        if self.ceiling == 0 {
            return Some(self.current_vector.clone());
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
        Some(self.current_vector.clone())
    }
}

impl<T> Sorter for BubbleSort<T>
where
    T: Clone + Copy + Ord,
{
    type ElementType = T;

    fn init(&mut self, v: Vec<T>) {
        self.ceiling = v.len() - 1;
        self.current_vector = v.clone();
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
