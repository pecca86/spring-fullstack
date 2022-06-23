import {useState, useEffect, Fragment} from 'react'
import {getAllStudents, deleteStudentById} from "./client";
import {
    Layout,
    Menu,
    Breadcrumb,
    Table, Spin, Empty, Button, notification, Avatar, Popconfirm, message, Radio, Divider
} from 'antd';
import {
    DesktopOutlined,
    PieChartOutlined,
    FileOutlined,
    TeamOutlined,
    UserOutlined,
    LoadingOutlined, DownloadOutlined, PlusOutlined
} from '@ant-design/icons';
import AddStudentBtn from "./AddStudentBtn";


import './App.css';
import {errorNotification} from "./Notification";

const {Header, Content, Footer, Sider} = Layout;
const {SubMenu} = Menu;


const deleteStudent = (studentId, callback) => {
    deleteStudentById(studentId).then(() => {
        message.success('Student Deleted');
        callback();
    });
}

// Alternative way of getting the student id from the HTML element and then delete
const confirm = (e) => {
    console.log(e.target.id);
    const studentId = e.target.id;
    deleteStudent(studentId);
    message.success('Student deleted');
};

const cancel = (e) => {
    console.log(e.target.name);
    message.error('Aborted');
};

const columns = fetchStudents => [
    {
      title: '',
        width: 60,
      dataIndex: 'avatar',
      key: 'avatar',
      render: (text, student) => {
        if (student.gender === 'MALE') {
            return <Avatar style={{ backgroundColor: '#308dff' }} icon={<UserOutlined />} />;
        } else if (student.gender === 'FEMALE') {
            return <Avatar style={{ backgroundColor: '#e691a3' }} icon={<UserOutlined />} />;
        } else {
            return <Avatar style={{ backgroundColor: '#54c46b' }} icon={<UserOutlined />} />;
        }
      }
    },
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
        width: 80
    },
    {
        title: 'First Name',
        dataIndex: 'firstName',
        key: 'firstName',
    },
    {
        title: 'Last Name',
        dataIndex: 'lastName',
        key: 'lastName'
    },
    {
        title: 'Email',
        dataIndex: 'email',
        key: 'email',
    },
    {
        title: 'Gender',
        dataIndex: 'gender',
        key: 'gender',
    },
    {
        title: 'Actions',
        dataIndex: 'actionButtons',
        key: 'actionButtons',
        render: (text, student) =>
            <Radio.Group>
                <Popconfirm
                    title="Are you sure to delete this student?"
                    //onConfirm={confirm}
                    onConfirm={() => deleteStudent(student.id, fetchStudents)}
                    onCancel={cancel}
                    okText={<p id={student.id}>Yes</p>}
                    cancelText="No"
                >
                    <Radio.Button>Delete</Radio.Button>
                    <a href={student.id}>Delete</a>
                </Popconfirm>
                <Radio.Button value="small">Edit</Radio.Button>
            </Radio.Group>
    }
];

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;

function App() {
    const [students, setStudents] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [fetching, setFetching] = useState(true);

    const fetchStudents = () =>
        getAllStudents()
            .then(res => res.json())
            .then(data => {
                setStudents(data);
                setFetching(false);
            }).catch(err => {
                err.response.json().then(res => {
                    errorNotification(
                        "There was an issue",
                        `${res.message} (${res.error} ${res.status})`
                    );
                });
        }); //.finally(() => setFetching(false))


    useEffect(() => {
        fetchStudents();
    }, []);

    const renderStudents = () => {
        if (fetching) {
            return <Spin indicator={antIcon} />
        }
        if (students.length <= 0) {
            return (
                <Fragment>
                    <AddStudentBtn fetchStudents={fetchStudents} />
                    <Empty />
                </Fragment>
            );
        }
        return <Table
            size="small"
            dataSource={students}
            columns={columns(fetchStudents)}
            bordered
            title={() => <AddStudentBtn fetchStudents={fetchStudents} />}
            pagination={{ pageSize: 10 }}
            scroll={{ y: 550 }}
            rowKey={(student) => student.id}
        />;
    }

    return <Layout style={{minHeight: '100vh'}}>
        <Sider collapsible collapsed={collapsed}
               onCollapse={setCollapsed}>
            <div className="logo"/>
            <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                <Menu.Item key="1" icon={<PieChartOutlined/>}>
                    Students
                </Menu.Item>
                <Menu.Item key="2" icon={<DesktopOutlined/>}>
                    Teachers
                </Menu.Item>
                <SubMenu key="sub1" icon={<UserOutlined/>} title="Current User">
                    <Menu.Item key="3">Log out</Menu.Item>
                    <Menu.Item key="4">Settings</Menu.Item>
                </SubMenu>
                <Menu.Item key="9" icon={<FileOutlined/>}>
                    Files
                </Menu.Item>
            </Menu>
        </Sider>
        <Layout className="site-layout">
            <Header className="site-layout-background" style={{padding: 0}}/>
            <Content style={{margin: '0 16px'}}>
                <Breadcrumb style={{margin: '16px 0'}}>
                    <Breadcrumb.Item>Students count</Breadcrumb.Item>
                    <Breadcrumb.Item>{students.length >= 0 ? students.length : <span>0</span>}</Breadcrumb.Item>
                </Breadcrumb>
                <div className="site-layout-background" style={{padding: 24, minHeight: 360}}>
                    {renderStudents()}
                </div>
            </Content>
            <Footer style={{textAlign: 'center'}}>
                By Pekka Solutions ⭐
                <Divider>
                    <a href="#">Link schmink</a>
                </Divider>
                ️</Footer>
        </Layout>
    </Layout>
}

export default App;