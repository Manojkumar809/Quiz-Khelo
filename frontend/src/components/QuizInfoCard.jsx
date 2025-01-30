import React from 'react'
import "../App.css"

const QuizInfoCard = ({ quizData }) => {
  return (
    <div className="quiz-Info">
      {console.log("Iam from info component")}
      <div>Title : {quizData.title}</div>
      <div>Topic : {quizData.topic}</div>
      <div>No of Questions : {quizData.questionsCount}</div>
      <div>Score : {quizData.score}</div>
    </div>
  )
}

export default QuizInfoCard
