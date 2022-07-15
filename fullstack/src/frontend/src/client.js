import fetch from 'unfetch';

const checkStatus = res => {
    if (res.ok) {
        return res;
    }
    const error = new Error(res.statusText);
    error.response = res;
    return Promise.reject(error);
}

export const getAllStudents = () =>
    fetch("api/v1/students")
        .then(checkStatus);


export const addNewStudent = (studentData) =>
    fetch("api/v1/students", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(studentData)
    }).then(checkStatus);


export const deleteStudentById = (studentId) =>
    fetch(`api/v1/students/${studentId}`, {
        method: 'DELETE',
    }).then(checkStatus)


export const updateStudent = (studentData, studentId) =>
    fetch(`api/v1/students/${studentId}`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'PUT',
        body: JSON.stringify(studentData)
    }).then(checkStatus);


