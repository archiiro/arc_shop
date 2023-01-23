export const listIdentificationType: any[] = [
  { id: 1, name: "Căn cước công dân" },
  { id: 2, name: "Chứng minh nhân dân" },
  { id: 3, name: "Giấy phép lái xe" },
  { id: 4, name: "Hộ chiếu" }
]

export const listGender: any[] = [
  { id: "M", name: 'Nam' },
  { id: "F", name: 'Nữ' },
  { id: "U", name: 'Khác' },
];
export const yesno: any[] = [
  { id: 1, name: "Có" },
  { id: 0, name: "Không" }
]
export const listTestResult: any[] = [
  { id: 1, name: "Có phản ứng" },
  { id: 2, name: "Không phản ứng" },
  { id: 3, name: "Không xác định" }
]
export const listConfirmTestResult: any[] = [
  { id: 1, name: "Âm tính" },
  { id: 2, name: "Dương tính" },
  { id: 3, name: "Không xác định" }
]
export const listTreatmentTransferResult: any[] = [
  { id: 1, name: "Thành công" },
  { id: 2, name: "Không thành công" }
]
export const isAgreeTest: any[] = [
  { id: true, name: "Có " },
  { id: false, name: "Không" },
]
export const listAlertType: any[] = [
  { id: "1", name: "Tự thông báo" },
  { id: "2", name: "Cùng thông báo" },
  { id: "3", name: "NVTV thông báo" },
  { id: "4", name: "Được phép tiết lộ danh tính người nhiễm HIV và NVTV thông báo" },
  { id: "5", name: "Không được phép tiết lộ danh tính người nhiễm HIV" }
]
export const listAgreePreTest: any[] = [
  { id: "1", name: "Có" },
  { id: "2", name: "Không" },
]
export const listRecentTestTime: any[] = [
  { id: 1, name: "Dưới 3 tháng" },
  { id: 2, name: "Từ 3 - 6 tháng" },
  { id: 3, name: "Từ 6 - 12 tháng" },
  { id: 4, name: "Trên 12 tháng" }
]

// Confirm Test

export const listScreeningTestResult: any[] = [
  { id: 0, name: "Không xác định" },
  { id: 1, name: "Có phản ứng" },
  { id: 2, name: "Không có phản ứng và có chỉ báo xét nghiệm nhiễm cấp" }
]
export const check: any[] = [
  { id: 1, name: "Không" },
  { id: 2, name: "Có" },
  { id: 3, name: "Không có thông tin" }
]
export const listReagentResult: any[] = [
  { id: 0, name: "Âm tính" },
  { id: 1, name: "Dương tính" },
  { id: 2, name: "Nghi ngờ" }];
export const listTransmissionRouteConfirm: any[] = [
  { id: '0', name: "Mã 1 - Lây qua đường máu" },
  { id: '1', name: "Mã 2 - Lây qua đường tình dục" },
  { id: '2', name: "Mã 1.1 - Lây qua đường tiêm chích ma túy" },
  { id: '3', name: "Mã 3 - Mẹ truyền sang con" },
  { id: '4', name: "Mã 1.2 - Truyền máu" },
  { id: '5', name: "Mã 4 - Không rõ" },
  { id: '6', name: "Mã 1.3 - Tai nạn nghề nghiệp" },
  { id: '7', name: "Mã 2.1 - Tình dục đồng giới" },
  { id: '8', name: "Mã 2.2 - Tình dục khác giới" }
];
export const listServiceCode: any[] = [
  { id: '0', name: "Dự phòng" },
  { id: '1', name: "Khoa sản" },
  { id: '2', name: "Khoa khám bệnh" },
  { id: '3', name: "Chương trình Lao" },
  { id: '4', name: "Khoa nhiễm" },
  { id: '5', name: "Lưu Động" },
  { id: '6', name: "Cấp cứu" },
  { id: '7', name: "Cố định" },
  { id: '8', name: "Không chuyên" },
  { id: '9', name: "Cơ sở khép kín" },
  { id: '10', name: "Bạn tình/bạn chích" }
];
export const listKQXNNewInfection: any = [
  { id: 1, name: "Nhiễm mới" },
  { id: 2, name: "Nhiễm lâu" },
]

export const ListSpecimenQuality: any = [
  { id: 0, name: "Không đạt" },
  { id: 1, name: "Đạt" }
];

export const listGSP_status: any = [
  { id: 0, name: "Chưa chuyển" },
  { id: 1, name: "Đã chuyển" },
]
export const listNewStatusXN: any = [
  { id: 0, name: "Không xét nghiệm" },
  { id: 1, name: "Có xét nghiệm" },
]
export const listConclusionOfInfection: any = [
  { id: 0, name: "Nhiễm mới" },
  { id: 1, name: "Nhiễm lâu" },
  { id: 2, name: "Không có thông tin" },
]
export const listKQXNVirus: any = [
  { id: '1', name: "Không phát hiện" },
  { id: '2', name: "Dưới ngưỡng phát hiện 200 bản sao/ml" },
  { id: '3', name: "Ngưỡng phát hiện từ 200 - 1000 bản sao/ml" },
  { id: '4', name: "Ngưỡng phát hiện >= 1000 bản sao/ml" },
]

//screening-test
export const listTransmissionRoute: any[] = [
  { id: 0, name: "Mã 1 - Lây qua đường máu" },
  { id: 1, name: "Mã 2 - Lây qua đường tình dục" },
  { id: 2, name: "Mã 1.1 - Lây qua đường tiêm chích ma túy" },
  { id: 3, name: "Mã 3 - Mẹ truyền sang con" },
  { id: 4, name: "Mã 1.2 - Truyền máu" },
  { id: 5, name: "Mã 4 - Không rõ" },
  { id: 6, name: "Mã 1.3 - Tai nạn nghề nghiệp" },
  { id: 7, name: "Mã 2.1 - Tình dục đồng giới" },
  { id: 8, name: "Mã 2.2 - Tình dục khác giới" }
];
export const listScreeningTestResults: any[] = [
  { id: 1, name: "Không phản ứng" },
  { id: 2, name: "Có phản ứng" },
  { id: 3, name: "Không xác định" },
  { id: 4, name: "Không có phản ứng và có chỉ báo xét nghiệm nhiễm cấp" },
]
export const listCustomerType: any[] = [
  { id: '1', name: 'Nội trú' },
  { id: '2', name: 'Ngoại trú' }
]
export const listTestAlgorithms: any[] = [
  { id: 1, name: 'Xét nghiệm kháng thể' },
  { id: 2, name: 'Xét nghiệm kháng nguyên, kháng thể' },
];
export const listAntigenAntibodyResults: any[] = [
  { id: 1, name: 'Kháng nguyên (+)' },
  { id: 2, name: 'Kháng thể (+)' },
  { id: 3, name: 'Kháng nguyên (+) và kháng thể (+)' },
];
export const listConfirmationTestTypes: any[] = [
  { id: 1, name: 'PCR HIV' },
  { id: 2, name: 'Xét nghiệm huyết thanh' }
];
export const listCounsellingServices: any[] = [
  { id: 1, name: 'Điều trị ARV' },
  { id: 2, name: 'Khám và sàng lọc lao' },
  { id: 3, name: 'Các bệnh lây truyền qua đường tình dục' },
  { id: 4, name: 'MMT' },
  { id: 5, name: 'Khác' }
]
export const listReferralSources: any[] = [
  { id: '1', name: 'Tiếp cận cộng đồng' },
  { id: '2', name: 'Kênh theo dấu bạn tình bạn chích' },
  { id: '3', name: 'Cán bộ y tế' },
  { id: '4', name: 'Internet, mạng xã hội' },
  { id: '5', name: 'Các kênh khác' }
]
export const listcounsellingService: any[] = [
  { id: 1, name: 'Dịch vụ A' },
  { id: 1, name: 'Dịch vụ B' }
];
export const listRecencyResult: any[] = [
  {id: '1', name: 'Nhiễm mới'},
  {id: '2', name: 'Nhiễm lâu'}
];
export const listVlResult: any[] = [
  {id: '1', name: 'Không phát hiện'},
  {id: '2', name: 'Dưới ngưỡng phát hiện 200 bản sao/ml'},
  {id: '3', name: 'Ngưỡng phát hiện từ 200 - 1000 bảnsao /ml'},
  {id: '4', name: 'Ngưỡng phát hiện >= 1000 bản sao/ml'}
];
export interface breadcrumb{
  link: string;
  text: string;
}

// VAAC Reports
export const listQuarter: any[] = [
  {id: '1', name: 'I'},
  {id: '2', name: 'II'},
  {id: '3', name: 'III'},
  {id: '4', name: 'IV'},
]
export const listYear: any[] = [
  {id: '1', name: '2001'},
  {id: '2', name: '2002'},
  {id: '3', name: '2003'},
  {id: '4', name: '2004'},
  {id: '5', name: '2005'},
  {id: '6', name: '2006'},
  {id: '7', name: '2007'},
  {id: '8', name: '2008'},
  {id: '9', name: '2009'},
  {id: '10', name: '2010'},
  {id: '11', name: '2011'},
  {id: '12', name: '2012'},
  {id: '13', name: '2013'},
  {id: '14', name: '2014'},
  {id: '15', name: '2015'},
  {id: '16', name: '2016'},
  {id: '17', name: '2017'},
  {id: '18', name: '2018'},
  {id: '19', name: '2019'},
  {id: '20', name: '2020'},
  {id: '21', name: '2021'},
  {id: '22', name: '2022'},
  {id: '23', name: '2023'},
]