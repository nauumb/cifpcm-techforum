import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/comments/';

class CommentsService {
    ask(post){
        return axios.post(API_URL + 'ask', {
            title: post.title,
            text: post.text,
            headers: authHeader()
        });
    }
}
export default new CommentsService();