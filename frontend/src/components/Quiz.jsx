import React, { useEffect, useRef, useState } from 'react'
import "../App.css"
import { useNavigate } from 'react-router-dom';

const Quiz = () => {
    const titleRef = useRef(null);
    const topicRef = useRef(null);
    const questionCountRef = useRef(null);
    const quizIdRef = useRef(null);
    const [quizTopics, setQuizTopics] = useState();
    const navigate = useNavigate();
    const getQuizTopics = async()=>{
      try {
        const quizTopics = await fetch(`http://localhost:8082/question/getTopics`);
        const quizTopicsRes = await quizTopics.json();
        if(quizTopicsRes){
          setQuizTopics(quizTopicsRes);
        }
      } catch (error) {
        console.log(error);
      }
    }
    useEffect(()=>{
      getQuizTopics();
    }, [])
    const handleCreateSubmit = async(e)=>{
      try {
        e.preventDefault();
        const title = titleRef.current.value;
        const topic = topicRef.current.value;
        const noOfQues = questionCountRef.current.value;
        const createQuiz = await fetch(`http://localhost:8082/quiz/create?title=${title}&topic=${topic}&count=${noOfQues}`)
        const createQuizRes = await createQuiz.json();
        createQuizRes?alert(`Your quiz Id is : ${createQuizRes}`):"";
        console.log(title, topic, noOfQues);
        } catch (error) {
          console.log(error);
        }
    }
    const handlePlaySubmit =async(e)=>{
      try {
        e.preventDefault();
        const quizId = quizIdRef.current.value;
        navigate("/playQuiz", {state:{quizId:quizId}});
      } catch (error) {
        console.log(error);
      }
    }
  return (
    <div className="quizComp">
      <div className="quizForms">
        <div className="qform">
            <form method="post" onSubmit={handleCreateSubmit}>
                <input type="text" name="title" placeholder="Quiz Title" ref={titleRef}/>
                <select name="topics" id="topics" ref={topicRef}>
                <option disabled>Select Topic</option>
                {quizTopics?quizTopics.map((it, ind)=>{
                  return <option key={ind} value={it}>{it.toUpperCase()}</option>
                }):(<option disabled>No Options Available</option>)}
                </select>
                <input type="number" name="quesCount" min="0" placeholder="Number of Questions"
                 ref={questionCountRef}/>
                <button type="submit" className="createQuiz">Create Quiz</button>
            </form>
        </div>
        <div className="qplay">
            <form method="post" onSubmit={handlePlaySubmit}>
                <input type="number" name="quizId" min="0" placeholder="Enter the Quiz Id" 
                ref={quizIdRef}/>
                <button type="submit" className="playQuiz">Play Quiz</button>
            </form>
        </div>
      </div>
    </div>
  )
}

export default Quiz