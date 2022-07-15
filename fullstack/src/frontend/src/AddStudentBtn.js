import { PlusOutlined } from '@ant-design/icons';
import { Button, Drawer, Space } from 'antd';
import { useState } from 'react';
import AddStudentForm from "./AddStudentForm";

const AddStudentBtn = ({fetchStudents, buttonText, clickedStudent}) => {
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
                {buttonText}
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
                    currentStudent={clickedStudent}
                />
            </Drawer>
        </>
    );
};

export default AddStudentBtn;