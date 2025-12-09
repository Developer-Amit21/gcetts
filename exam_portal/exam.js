// Collect exam answers and submit to backend
async function submitExam() {
    const roll = document.getElementById("stuRoll").value.trim();
    const exam = document.getElementById("examSelect").value;
    const answers = document.getElementById("answers").value.trim();

    if (!roll || !exam || !answers) {
        alert("Please fill all fields before submitting the exam!");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/api/exam", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ roll, exam, answers })
        });

        const data = await response.json();

        if (data.status) {
            alert(data.status);
            document.getElementById("examForm").reset();
        } else {
            alert(data.error || "Submission failed");
        }

    } catch (error) {
        alert("Error connecting to server. Make sure backend is running.");
        console.error("Exam submission error:", error);
    }
}

// Optional: Load exam questions dynamically
function loadExamQuestions() {
    const examArea = document.getElementById("examQuestions");
    const exam = document.getElementById("examSelect").value;

    if (!exam) {
        alert("Please select an exam!");
        return;
    }

    let questionsHTML = "";

    if (exam === "mid1") {
        questionsHTML = `
            <p>1. What is Java?</p>
            <input type="text" placeholder="Answer">
            <p>2. Define polymorphism.</p>
            <input type="text" placeholder="Answer">
        `;
    } else if (exam === "mid2") {
        questionsHTML = `
            <p>1. What is HTML?</p>
            <input type="text" placeholder="Answer">
            <p>2. What is CSS?</p>
            <input type="text" placeholder="Answer">
        `;
    } else if (exam === "final") {
        questionsHTML = `
            <p>1. Explain SDLC.</p>
            <input type="text" placeholder="Answer">
            <p>2. What is OOPS?</p>
            <input type="text" placeholder="Answer">
        `;
    }

    examArea.innerHTML = questionsHTML;
}
