import { NavigationItem } from "src/@vex/interfaces/navigation-item.interface";

export const navigation: NavigationItem[] = [
  {
    type: 'link',
    label: 'Tổng quan',
    icon: 'mat:insights',
    route: '/community-test/create',
    routerLinkActiveOptions: { exact: true }
  },
  {
    type: 'dropdown',
    label: 'Báo cáo kết quả hoạt động giảm thiểu tác hại',
    icon: 'mat:settings',
    children: [
      {
        type: 'link',
        label: 'Nhập báo cáo',
        route: '/hri-summary-reports/create',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'link',
        label: 'Danh sách báo cáo',
        route: '/hri-summary-reports/dashboard',
        routerLinkActiveOptions: { exact: true }
      }
    ]
  },
  {
    type: 'dropdown',
    label: 'Cấu hình',
    icon: 'mat:settings',
    children: [
      {
        type: 'link',
        label: 'Công việc',
        route: '/setting/occupation',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'link',
        label: 'Dân tộc',
        route: '/setting/ethnics',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'link',
        label: 'Quốc gia',
        route: '/setting/country',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'link',
        label: 'Đối tượng có nguy cơ cao',
        route: '/setting/populationGroup',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'link',
        label: 'Hành vi nguy cơ',
        route: '/setting/riskbehaviors',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'link',
        label: 'Địa danh hành chính',
        route: '/setting/location',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'link',
        label: 'Người có nguy cơ nhiễm HIV',
        route: '/setting/hivriskperson',
        routerLinkActiveOptions: { exact: true }
      },
    ]
  },

];
