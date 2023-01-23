import { PopulationGroup } from "../_models/population-group.model";

export interface SearchObject {
    keyword?: string;
    pageIndex: number;
    pageSize: number;
}
export interface SearchConfirmTest {
  keyword?: string;
  pageIndex: number;
  pageSize: number;
  code: string;
  personName: string;
  confirmationDateFrom: null;
  confirmationDateTo: null;
  confirmTestResult: number;
  // specimenCollectedLab: any[];

  // gSP_status: number;

  // earlyHiv: string;
  // earlyHivDateFrom: null;
  // earlyHivDateTo: null;
  // virusLoad: number;
}
export interface SearchScreeningTest {
  tab: number;
  pageIndex: number;
  pageSize: number;
  code: string;// mã khách hàng
  personName: string;// tên khách hangf
  advisoryeTimeFrom: string;// ngày đến
  advisoryeTimeTo: string;// ngày đi
  screeningTestResult: number;//kêt quả xét nhiệm sang loc tai co so
  listGroupDto: PopulationGroup[];// nhom doi tuong
  serviceCode: [];// nguon
}

export interface SearchCommunityTest{
  tab: number;
  keyword?: string;
  pageIndex: number;
  pageSize: number;
  code: string;
  personName: string;
  testDate: string,
  sendStatus: string,
}
