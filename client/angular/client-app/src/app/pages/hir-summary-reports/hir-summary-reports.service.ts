import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Ethnics } from 'src/app/_models/ethnics.model';
import { ConfigInitService } from 'src/app/init/config-init.service';
import { SearchObject } from 'src/app/_interfaces/search-object';
import { HriSummaryReports } from 'src/app/_models/hri-summary-reports.model';

@Injectable({
  providedIn: 'root'
})

export class HirSummaryReportsService {

  constructor(private http: HttpClient, private configInitService: ConfigInitService) { }

  private readonly END_POINT = this.configInitService.apiBaseUrl + '/api/v1/hrisummaryreports';

  pagingHriSummaryReports(searchObject: SearchObject): Observable<any> {
    const url = this.END_POINT + "/paging"
    return this.http.post<any>(url, searchObject, { responseType: "json" });
  }

  createHriSummaryReports(id: string): Observable<any> {
    let url = this.END_POINT + "/create"
    return this.http.get<any>(url, { responseType: "json" });
  }

  saveHirSummaryReports(dto: HriSummaryReports): Observable<any> {
    var requestHeaders = new HttpHeaders();
    let url = this.END_POINT + "/save";
    return this.http.post<HriSummaryReports>(url, dto, { headers: requestHeaders, responseType: "json" });
  }

  deleteHirSummaryReports(id: string): Observable<any> {
    let url = this.END_POINT + "/" + id
    return this.http.delete<any>(url, { responseType: "json" });
  }
}
