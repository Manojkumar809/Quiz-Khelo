import React, { useEffect, useState } from 'react'
import QuizCard from './QuizCard'
import { useLocation } from 'react-router-dom';
import LoadingSpinner from './LoadingSpinner';

const PlayQuiz = () => {
  const [quizQuestions, setQuizQuestions] = useState([]);
  const [quizResponse, setQuizResponse] = useState([]);
  const [loading, setLoading] = useState(false);
  const [score, setScore] = useState(null);
  const location = useLocation();
  const quizId = location.state.quizId;
  const getQuizQuestions = async()=>{
    try {
      setLoading(true);
      const playQuiz = await fetch(`http://localhost:8082/quiz/getQuiz?quizId=${quizId}`);
      const playQuizRes = await playQuiz.json();
      setTimeout(()=>{
        if(playQuizRes){
          setLoading(false);
          setQuizQuestions(playQuizRes);
        }
      }, 1000)
    } catch (error) {
      console.log(error);
    }
  }
  const getQuizResponse = (response)=>{{
    setQuizResponse(prevData => [...prevData, {id: response.id, answer:response.answer}]);
  }}
  const submitResponses = async()=>{
    try {
      setLoading(true);
      const quizScore = await fetch(`http://localhost:8082/quiz/score?quizId=${quizId}`, {
        method:"POST",
        headers:{
          "Content-Type": "application/json"
        },
        body:JSON.stringify(quizResponse),
      });
      const quizScoreRes = await quizScore.json();
      if(quizScoreRes){
        setLoading(false);
        setScore(quizScoreRes);
      }
    } catch (error) {
      console.log(error);
    }
  }
  useEffect(() => {
    const handleBeforeUnload = (event) => {
        event.returnValue = "Reload Warning";
    };
    window.addEventListener('beforeunload', handleBeforeUnload);
    return () => {
        window.removeEventListener('beforeunload', handleBeforeUnload);
    };
  }, []);

    return (
        <div>
        <div className="quizInfo"><button onClick={getQuizQuestions}>Start quiz</button>
        <button onClick={submitResponses}>Submit quiz</button></div>
        {loading && <LoadingSpinner />}
        {score !== null && (<div className="scoreSection"><h2>Your Score is: {score}</h2></div>)}
        {quizQuestions?quizQuestions.map((quiz, ind)=>{
            return(<QuizCard key={ind} quizData={quiz} sno={ind+1} sendData={getQuizResponse} />)
        }):""}
        </div>
    )
}

export default PlayQuiz