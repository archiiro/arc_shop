import { NavigationItem } from "src/@vex/interfaces/navigation-item.interface";

export const navigation: NavigationItem[] = [
    {
        type: 'dropdown',
        label: 'Tổng quan',
        icon: 'mat:insights',
        children: [
            {
                type: 'link',
                label: 'Cảnh báo tình hình dịch',
                route: '/dashboard-alert',
                routerLinkActiveOptions: { exact: true }
            },
            {
                type: 'link',
                label: 'Phân tích tình hình dịch',
                route: '/dashboard-total',
                routerLinkActiveOptions: { exact: true }
            },
            {
                type: 'link',
                label: 'tư vấn & xét nghiệm',
                route: '/dashboard-htc.',
                routerLinkActiveOptions: { exact: true }
            },
            {
                type: 'link',
                label: 'Xét nghiệm nghiễm mới',
                route: '/dashboard-ric',
                routerLinkActiveOptions: { exact: true }
            },
            {
                type: 'link',
                label: 'Quản lý người nhiễm',
                route: '/dashboard-pac',
                routerLinkActiveOptions: { exact: true }
            },
            {
                type: 'link',
                label: 'Điều trị ngoại trú',
                route: '/dashboard-opc',
                routerLinkActiveOptions: { exact: true }
            },
        ]
    }
]