import {notification} from "antd";

const openNotificationWithIcon = (type, message, description) => {
    notification[type]({message, description});
};

export const successNotification = (message, description) =>
    openNotificationWithIcon('success', message, description);

export const errorNotification = (message, description) =>
    openNotificationWithIcon('error', message, description);