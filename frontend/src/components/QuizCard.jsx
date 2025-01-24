import React from 'react'
import { useState } from 'react';

const QuizCard = ({quizData, sno, sendData}) => {
    const {id, difficultyLevel, questionTitle, option1, option2, option3, option4} = quizData;
    const options = [option1,option2,option3,option4];
    const [clickedOption, setClickedOption] = useState(null);
    const handleOptionClick = (option) =>{
        setClickedOption(option);
        const quizResponse = {id:id, answer:option};
        sendData(quizResponse);
    }
    const getDifficultyColor =(difficulty) =>{
        switch (difficulty) {
            case "easy":
                return "Green"
            case "medium":
                return "Yellow"
            case "hard":
                return "Red"
            default:
                return "Black"
        }
    }
  return (
    <div className='quizCardComp'>
      <div className="container">
        <div className="ques">
        <span className="quesNo">{sno}.</span>
        <span className="difficulty"
        style={{color:getDifficultyColor(difficultyLevel.toLowerCase())}}>{difficultyLevel}</span>
            {questionTitle}</div>
        <div className="optionSec">
            {options?options.map((option, ind)=>(
                <button
                key={ind}
                onClick={()=>handleOptionClick(option)}
                disabled={clickedOption !== null} 
                style={clickedOption === option? {backgroundColor:"#ff5722"}:{}}
                >{option}</button>
            )):""}
        </div>
      </div>
    </div>
  )
}

export default QuizCard
