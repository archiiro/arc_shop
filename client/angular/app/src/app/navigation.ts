import { NavigationItem } from "src/@vex/interfaces/navigation-item.interface";

export const navigation: NavigationItem[] = [
  {
    type: 'dropdown',
    label: 'XN tại cộng đồng',
    icon: 'mat:verified_user',
    children: [
      {
        type: 'link',
        label: 'Tổng quan',
        route: '',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'link',
        label: 'Khách hàng mới',
        route: '/community-test/create',
        routerLinkActiveOptions: { exact: true }
      }
    ]
  }
];
