import axios from 'axios'
import authHeader from './auth-header'

const API_URL = 'http://localhost:8080/comments/'

class CommentsService {
  ask(post) {
    return axios.post(
      API_URL + 'post',
      {
        title: post.title,
        text: post.text,
        parent_id: post.parent_id,
      },
      { headers: authHeader() }
    )
  }
  edit(post) {
    return axios.put(
      API_URL + post.id + '/edit',
      {
        title: post.title,
        text: post.text,
      },
      { headers: authHeader() }
    )
  }
  delete(postId) {
    return axios.delete(
      API_URL + postId + '/delete',
      { headers: authHeader() }
    )
  }
  get(id) {
    return axios.get(API_URL + id, {})
  }
  getThread(parent_id){
    return axios.get(API_URL + parent_id + '/thread')

  }
}
export default new CommentsService()
