export default class Comment {
  constructor(title, text, parent_id) {
    if (parent_id != '') {
      this.title = title
      this.text = text
      this.parent_id = parent_id
    } else {
      this.title = title
      this.text = text
    }
  }
}
