import { NavigationItem } from "src/@vex/interfaces/navigation-item.interface";

export const navigation: NavigationItem[] = [
  {
    type: 'dropdown',
    label: 'Tổng quan',
    icon: 'mat:insights',
    children: [
      {
        type: 'link',
        label: 'Báo cáo theo quý',
        route: '/reprot-quater',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'link',
        label: 'Báo cáo theo năm',
        route: '/report-year',
        routerLinkActiveOptions: { exact: true }
      },
    ]
  },
  {
    type: 'dropdown',
    label: 'Nhập báo cáo',
    icon: 'mat:insights',
    children: [
      {
        type: 'link',
        label: 'Báo cáo theo quý',
        route: '/reprot-quater',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'link',
        label: 'Báo cáo theo năm',
        route: '/report-year',
        routerLinkActiveOptions: { exact: true }
      },
    ]
  },
  {
    type: 'dropdown',
    label: 'Hóa đơn',
    icon: 'mat:today',
    children: [
      {
        type: 'link',
        label: 'Hóa đơn bán',
        route: '/bill/sale',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'link',
        label: 'Hóa đơn nhập',
        route: '/bill/invoice',
        routerLinkActiveOptions: { exact: true }
      },
    ]
  },
  {
    type: 'dropdown',
    label: 'Sản phẩm',
    icon: 'mat:store',
    children: [
      {
        type: 'dropdown',
        label: 'Loại sản phẩm',
        children: [
          {
            type: 'link',
            label: 'Bánh kẹo',
            route: '/product/type/confectionery',
            routerLinkActiveOptions: { exact: true }
          },
          {
            type: 'link',
            label: 'Đồ khô',
            route: '/product/type/dry-food',
            routerLinkActiveOptions: { exact: true }
          },
          {
            type: 'link',
            label: 'Đồ ăn nhanh',
            route: '/product/type/fast-food',
            routerLinkActiveOptions: { exact: true }
          },
          
          {
            type: 'link',
            label: 'Đồ có cồn',
            route: '/product/type/acol-drink',
            routerLinkActiveOptions: { exact: true }
          },
          {
            type: 'link',
            label: 'Nước ngọt',
            route: '/product/type/soft-drink',
            routerLinkActiveOptions: { exact: true }
          },
        ]
      },
      {
        type: 'dropdown',
        label: 'Tồn kho',
        children: [
          {
            type: 'link',
            label: 'Dưới định mức tồn',
            route: '/product/inventory/under',
            routerLinkActiveOptions: { exact: true }
          },
          {
            type: 'link',
            label: 'Trên định mức tồn',
            route: '/product/inventory/under',
            routerLinkActiveOptions: { exact: true }
          },
          {
            type: 'link',
            label: 'Còn hàng',
            route: '/product/inventory/under',
            routerLinkActiveOptions: { exact: true }
          },
          {
            type: 'link',
            label: 'Hết hàng',
            route: '/product/inventory/under',
            routerLinkActiveOptions: { exact: true }
          },
          {
            type: 'link',
            label: 'Ngưng bán',
            route: '/product/inventory/under',
            routerLinkActiveOptions: { exact: true }
          },
        ]
      }
    ]
  },
  {
    type: 'dropdown',
    label: 'Đơn vị',
    icon: 'mat:group_work',
    children: [
      {
        type: 'link',
        label: 'Nhà cung cấp',
        route: '/unit/site',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'link',
        label: 'Đơn vị giao hàng',
        route: '/unit/staffs',
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
        label: 'Đơn vị hành chính',
        route: '/setting/administrative',
        routerLinkActiveOptions: { exact: true }
      },
      {
        type: 'dropdown',
        label: 'Tham số',
        children: [
          {
            type: 'link',
            label: 'Quốc gia',
            route: '/setting/parameter/country',
            routerLinkActiveOptions: { exact: true }
          },
          {
            type: 'link',
            label: 'Dân tộc',
            route: '/setting/parameter/ethnics',
            routerLinkActiveOptions: { exact: true }
          },
          {
            type: 'link',
            label: 'Tôn giáo',
            route: '/setting/parameter/religion',
            routerLinkActiveOptions: { exact: true }
          },
          {
            type: 'link',
            label: 'Nghề nghiệp',
            route: '/setting/parameter/occupation',
            routerLinkActiveOptions: { exact: true }
          }
        ]
      },
      {
        type: 'dropdown',
        label: 'Người dùng',
        children: [
          {
            type: 'link',
            label: 'Quản lý',
            route: '/setting/user/admin',
            routerLinkActiveOptions: { exact: true }
          },
          {
            type: 'link',
            label: 'Nhân viên',
            route: '/setting/user/staff',
            routerLinkActiveOptions: { exact: true }
          },
          {
            type: 'link',
            label: 'Khách hàng',
            route: '/setting/user/customer',
            routerLinkActiveOptions: { exact: true }
          }
        ]
      },
    ]
  }
];
