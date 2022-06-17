import {Button, Checkbox, Form, Input, Row, Select, Spin} from 'antd';
import {Option} from "antd/es/mentions";
import {addNewStudent} from "./client";
import {useEffect, useState} from "react";
import {successNotification, errorNotification} from "./Notification";

const AddStudentForm = ({onClose, fetchStudents}) => {

    const [submitting, setSubmitting] = useState(false);


    const onFinish = (values) => {
        console.log('Success:', values);
        //setSubmitting(true);
        setSubmitting(true);
        console.log(submitting)
        addNewStudent(values)
            .then(() => {
                console.log("Student added");
                onClose();
                fetchStudents();
                successNotification(
                    "Student added",
                    `${values.firstName} was added!`)
            }).catch(err => {
                console.log(err);
        }).finally(setSubmitting(false));
    };

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    return (
        <Form
            name="basic"
            labelCol={{
                span: 8,
            }}
            wrapperCol={{
                span: 16,
            }}
            initialValues={{
                remember: true,
            }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
        >
            <Form.Item
                label="First Name"
                name="firstName"
                rules={[
                    {
                        required: true,
                        message: 'Please input your first name!',
                    },
                ]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                label="Last Name"
                name="lastName"
                rules={[
                    {
                        required: true,
                        message: 'Please input your last name!',
                    },
                ]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                label="Email"
                name="email"
                rules={[
                    {
                        required: true,
                        message: 'Please input your first email!',
                    },
                ]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                name="gender"
                label="gender"
                rules={[{required: true, message: 'Please select a gender'}]}
            >
                <Select placeholder="Please select a gender">
                    <Option value="MALE">MALE</Option>
                    <Option value="FEMALE">FEMALE</Option>
                    <Option value="OTHER">OTHER</Option>
                </Select>
            </Form.Item>

            <Form.Item
                wrapperCol={{
                    offset: 8,
                    span: 16,
                }}
            >
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item>

            <Form.Item>
                {submitting && <Spin />}
            </Form.Item>
        </Form>
    );
};

export default AddStudentForm;