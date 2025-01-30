import React, { useState, useEffect } from 'react'
import "../App.css";
import QuizInfoCard from './QuizInfoCard';
import { useNavigate } from 'react-router-dom';

const UserProfile = () => {
    const [quizTable, setQuizTable] = useState([]);
    const [noOfQuiz, setNoOfQuiz] = useState(null);
    const [averageScore, setAverageScore] = useState(null);
    const navigate = useNavigate();
    const username = JSON.parse(sessionStorage.getItem("username"));
    useEffect(()=>{
        const userToken = JSON.parse(sessionStorage.getItem("token"));
        if(userToken == null){
        navigate("/login");
        }
    }, [])
    const handleGetPerformance = async()=>{
        const performance = await fetch(`http://localhost:8083/quiz/performance?username=${username}`, {
            method:"GET",
            headers:{
                "Content-Type":"Application/json",
                "Authorization":`Bearer ${JSON.parse(sessionStorage.getItem("token"))}`,
            }
        });
        const performanceRes = await performance.json();
        if(performanceRes){
            setQuizTable(performanceRes.quizData);
            setNoOfQuiz(performanceRes.quizzesCount);
            setAverageScore(performanceRes.averageScore);
        }
    }
  return (
    <div className="user-profile-comp">
        <div className="user-name"><h1>{`Welcome ${username}!`}</h1>
        <button onClick={handleGetPerformance}>Get Performance</button></div>
        <div className="user-data">
            <div className="quiz-data">
                {quizTable? quizTable.map((data, ind)=>{
                    return <QuizInfoCard key={ind} quizData={data} />
                }):""}
            </div>
            <div className="quiz-stats">
                {noOfQuiz?<h1>You played {noOfQuiz} quizzes</h1>:<h1>You played {noOfQuiz} quizzes</h1>}
                <div className="stats-circle" style={{background: `linear-gradient(to top, #334fdb94 ${averageScore}%, transparent ${averageScore}%)`}}>
                    {averageScore?`${averageScore}% Average Score`:"0"}
                </div>
            </div>
        </div>
    </div>
  )
}

export default UserProfile
