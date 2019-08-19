import Category from "./category";

export default interface Skill {
    id: string,
    label: string,
    level: string,
    category: Category
}