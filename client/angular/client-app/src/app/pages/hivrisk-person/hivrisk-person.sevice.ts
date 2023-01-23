import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { ConfigInitService } from 'src/app/init/config-init.service';
import { SearchObject } from 'src/app/_interfaces/search-object';
import { HIVRiskPerson } from 'src/app/_models/hivriskperson.model';

@Injectable({
  providedIn: 'root'
})

export class HIVRiskPersonService {

  constructor(private http: HttpClient, private configInitService: ConfigInitService) { }

  private readonly END_POINT = this.configInitService.apiBaseUrl + '/api/v1/hivriskperson';

  // phan trang method post
  pagingHIVRiskPerson(searchObject: SearchObject): Observable<any> {
    const url = this.END_POINT + "/paging"
    return this.http.post<any>(url, searchObject,
      { responseType: "json" }
    );
  }
  getHIVRiskPerson(id: string): Observable<HIVRiskPerson> {
    let url = this.END_POINT + "/" + id
    return this.http.get<HIVRiskPerson>(url, { responseType: "json" });
  }
  saveOrUpdateHIVRiskPerson(HIVRiskPerson: HIVRiskPerson): Observable<HIVRiskPerson> {
    var requestHeaders = new HttpHeaders();
    let url = this.END_POINT + "/save";
    return this.http.post<HIVRiskPerson>(url, HIVRiskPerson, { headers: requestHeaders, responseType: "json" });
  }
  deleteHIVRiskPerson(id: string): Observable<HIVRiskPerson> {
    let url = this.END_POINT + "/" + id
    return this.http.delete<HIVRiskPerson>(url, { responseType: "json" });
  }
  ///getAll
  // getAll(): Observable<any> {
  //   let url = this.END_POINT + "/getAll"
  //   return this.http.get<any>(url, { responseType: "json" });
  // }
}
