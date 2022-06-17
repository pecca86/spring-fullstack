import { PlusOutlined } from '@ant-design/icons';
import { Button, Col, DatePicker, Drawer, Form, Input, Row, Select, Space } from 'antd';
import { useState } from 'react';
import AddStudentForm from "./AddStudentForm";
const { Option } = Select;

const AddStudentBtn = ({fetchStudents}) => {
    const [visible, setVisible] = useState(false);

    const showDrawer = () => {
        setVisible(true);
    };

    const onClose = () => {
        setVisible(false);
    };

    return (
        <>
            <Button type="primary" onClick={showDrawer} icon={<PlusOutlined />}>
                New Student
            </Button>
            <Drawer
                title="Create a new account"
                width={720}
                onClose={onClose}
                visible={visible}
                bodyStyle={{
                    paddingBottom: 80,
                }}
                extra={
                    <Space>
                        <Button onClick={onClose}>Cancel</Button>
                    </Space>
                }
            >
                <AddStudentForm
                    onClose={onClose}
                    fetchStudents={fetchStudents}
                />
            </Drawer>
        </>
    );
};

export default AddStudentBtn;