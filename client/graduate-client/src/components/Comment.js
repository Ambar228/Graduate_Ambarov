import React, {useEffect, useState} from 'react';
import axios from "axios";
import jwtDecode from "jwt-decode";

const Comment = ({comments, onAddComment, eventId}) => {
    const [newComment, setNewComment] = useState('');
    const [updateComment, setUpdateComment] = useState('');
    const [selectedFile, setSelectedFile] = useState(null);
    const [isOpen, setIsOpen] = useState(false)

    const numberPhone = jwtDecode(localStorage.getItem('token')).sub

    const [role, setRole] = useState('USER')

    useEffect(() => {
            axios.get("http://localhost:9000/user/check", {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            }).then(response => {
                setRole(response.data)
            })
        }, []
    )

    const handleFileInputChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const handleAddComment = () => {
        onAddComment(newComment);

        const formData = new FormData()
        if (selectedFile) {
            formData.append('file', selectedFile)
        }
        formData.append('text', newComment)

        axios.post('http://localhost:9000/comment/' + eventId, formData, {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        }).then()

        setNewComment('');
    };

    const handleUpdateComment = (comment) => {
        const formData = new FormData()
        if (comment.file) {
            formData.append('file', comment.file)
        }
        formData.append('text', updateComment)

        axios.put('http://localhost:9000/comment/' + comment.id + '?eventId=' + eventId, formData, {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        }).then()

        setUpdateComment('');
    }

    const handleDeleteComment = (comment) => {
        axios.delete('http://localhost:9000/comment/' + comment.id + '?eventId=' + eventId, {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        }).then()
    }

    return (
        <div>
            <h3>Комментарии</h3>
            {comments !== undefined && comments.length === 0 &&
                <p style={{fontSize: 18}}>Комментариев пока нет.</p>}
            {comments !== undefined && comments.map((comment) => (
                <div key={comment.id}>
                    {
                        comment.user.numberPhone === numberPhone
                            ? <>
                                <span style={{fontSize: 18, paddingRight: 5}}>{comment.text}</span>
                                <span style={{fontSize: 18, paddingRight: 5}} onClick={() => setIsOpen(true)}>✎</span>
                                <span style={{fontSize: 20}} onClick={() => handleDeleteComment(comment)}>x</span>
                                <br/>
                                {isOpen &&
                                    <>
                                        <input type={"text"} value={updateComment}
                                               onChange={e => setUpdateComment(e.target.value)}/>
                                        <span style={{fontSize: 18, paddingLeft: 10}}
                                              onClick={() => handleUpdateComment(comment)}>&#x2713;</span>
                                        <span style={{fontSize: 20, paddingLeft: 10, color: 'red'}}
                                              onClick={() => setIsOpen(false)}>←</span>
                                        <br/> <br/>
                                    </>
                                }
                            </>
                            :
                            <>
                                {role === 'MODERATOR' ?
                                    <>
                                        <span style={{fontSize: 18, paddingRight: 5}}>{comment.text}</span>
                                        <span style={{fontSize: 20}}
                                              onClick={() => handleDeleteComment(comment)}>x</span>
                                        <br/>
                                    </>
                                    :
                                    <p style={{fontSize: 18}}>{comment.text}</p>
                                }
                            </>
                    }
                    <p style={{fontSize: 18}}>Автор: {comment.user.name + ' ' + comment.user.lastname}</p>
                </div>
            ))}
            <div>
                <h4>Добавить комментарий</h4>
                <input style={{width: 500, marginBottom: 50}} type="text" value={newComment}
                       onChange={(e) => setNewComment(e.target.value)}/>
                <input type={'file'} onChange={handleFileInputChange}/>
                <button style={{marginLeft: 10}} onClick={handleAddComment}>Отправить</button>
            </div>
        </div>
    );
};

export default Comment;