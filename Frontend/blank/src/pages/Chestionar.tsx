import React, { useState } from 'react';
import './style/Chestionar.css';

const Chestionar: React.FC = () => {
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [answers, setAnswers] = useState<number[]>([]);
  const [result, setResult] = useState<number | null>(null);

  const questions = [
    {
      id: 1,
      question: "How would you best describe your diet?",
      options: ["Meat every day", "Meat at some meals", "Meat very rarely", "Vegetarian", "Vegan"],
      values: [25, 12, 4, 7, 3],
    },
    {
      id: 2,
      question: "How often do you take the bus?",
      options: ["1-2 times a week", "3-5 times a week", "everyday"],
      values: [0.5, 1.5, 2],
    },
    {
      id: 3,
      question: "Of the food you buy, how much is wasted and thrown away?",
      options: ["None", "0%-10%", "10%-30%", "More than 30%"],
      values: [0, 2, 5, 10],
    },
    {
      id: 4,
      question: "What kind of vehicle do you travel in most often as driver or passenger?",
      options: ["Car", "Motorbike", "Bus", "Bike", "I walk"],
      values: [25, 10, 5, 1, 0],
    },
    {
      id: 5,
      question: "What kind of house do you live in?",
      options: ["Detached", "Semi detached", "Studio"],
      values: [0, 5, 2],
    },
    {
      id: 6,
      question: "How many rooms does your house have?",
      options: ["1 room", "2 rooms", "3 rooms", "more than 4 rooms"],
      values: [5, 8, 12, 15],
    },
    {
      id: 7,
      question: "How many people live in your house?",
      options: ["1", "2", "3", "4","more than 5"],
      values: [1, 2, 3, 4, 7],
    },
    {
      id: 8,
      question: "How do you heat your home?",
      options: ["Gas", "Electricity", "Wood"],
      values: [15, 10, 3],
    },
    {
      id: 9,
      question: "How warm do you keep your home in winter?",
      options: ["14-17 Celsius", "18-21 Celsius", "22-24 Celsius", "More than 24 Celsius"],
      values: [5, 8, 12, 15],
    },
    {
      id: 10,
      question: "In the last 12 months, have you bought any of these new household items?",
      options: ["TV, laptop or PC", "Large item of furniture", "Washing machine, dishwasher, fridge, freezer", "Mobile phone or tablet", "None of the above"],
      values: [10, 5, 8, 6, 0],
    },
    {
      id: 11,
      question: "In a typical month, how much do you spend on clothes and footwear?",
      options: ["0 lei", "1-150 lei", "150-300 lei", "more than 300 lei"],
      values: [0, 5, 10, 15],
    },
    {
      id: 12,
      question: "How much do you spend on your pets and their food?",
      options: ["I don`t have a pet", "1-50 lei", "50 - 200 lei", "More than 200 lei"],
      values: [0, 2, 8, 10],
    },
    {
      id: 13,
      question: "In a typical month, how much do you spend on health?",
      options: ["0 - 50 lei", "50 - 200 lei", "More than 200 lei"],
      values: [0, 5, 10],
    },
    {
      id: 14,
      question: "In a typical month, how much do you spend on your hobbies?",
      options: ["0-50 lei", "50- 300 lei", "more than 300 lei"],
      values: [0, 5, 10],
    },
    {
      id: 15,
      question: "Which of these types of waste do you recycle and/or compost?",
      options: ["Food", "Paper", "Tin cans", "Plastic", "Glass", "None"],
      values: [1, 2, 3, 4, 5, 0],
    },
    


  ];

  const handleAnswer = (value: number) => {
    const updatedAnswers = [...answers];
    updatedAnswers[currentQuestionIndex] = value;
    setAnswers(updatedAnswers);
  };

  const handleNext = () => {
    if (currentQuestionIndex < questions.length - 1) {
      setCurrentQuestionIndex((prev) => prev + 1);
    } else {
      calculateCarbonFootprint();
    }
  };

  const handleBack = () => {
    if (currentQuestionIndex > 0) {
      setCurrentQuestionIndex((prev) => prev - 1);
    }
  };

  const calculateCarbonFootprint = () => {
    const total = answers.reduce((acc, curr) => acc + (curr || 0), 0);
    setResult(total);
  };

  return (
    <div className="container">
      {result === null ? (
        <>
          <div className="question">{questions[currentQuestionIndex].question}</div>
          <div className="button-group">
            {questions[currentQuestionIndex].options.map((option, index) => (
              <button
                key={option}
                className={answers[currentQuestionIndex] === questions[currentQuestionIndex].values[index] ? "selected" : ""}
                onClick={() => handleAnswer(questions[currentQuestionIndex].values[index])}
              >
                {option}
              </button>
            ))}
          </div>
          <div className="navigation">
            <button onClick={handleBack} disabled={currentQuestionIndex === 0}>
              Back
            </button>
            <button onClick={handleNext}>
              {currentQuestionIndex === questions.length - 1 ? "Finish" : "Continue"}
            </button>
          </div>
        </>
      ) : (
        <div className="result">
          Your carbon footprint score is <strong>{result}</strong>.
        </div>
      )}
    </div>
  );
};

export default Chestionar;
