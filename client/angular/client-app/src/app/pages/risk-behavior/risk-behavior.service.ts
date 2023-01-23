import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from "@angular/common/http";
// import { Occupation } from 'src/app/_models/occupation.model';
import { ConfigInitService } from 'src/app/init/config-init.service';
import { SearchObject } from 'src/app/_interfaces/search-object';
import { RiskBehavior } from 'src/app/_models/risk-behavior.model';

@Injectable({
  providedIn: 'root'
})

export class RiskBehaviorService {

  constructor(private http: HttpClient, private configInitService: ConfigInitService) { }

  private readonly END_POINT = this.configInitService.apiBaseUrl + '/api/v1/riskbehaviors';

  // phan trang method post
  pagingRiskBehavior(searchObject: SearchObject): Observable<any> {
    const url = this.END_POINT + "/paging"
    return this.http.post<any>(url, searchObject,
      { responseType: "json" }
    );
  }

  getRiskBehavior(id: string): Observable<RiskBehavior> {
    let url = this.END_POINT + "/" + id
    return this.http.get<RiskBehavior>(url, { responseType: "json" });
  }

  saveOrUpdateRiskBehavior(riskBehavior: RiskBehavior): Observable<RiskBehavior> {
    var requestHeaders = new HttpHeaders();
    let url = this.END_POINT + "/save"
    return this.http.post<RiskBehavior>(url, riskBehavior, { headers: requestHeaders, responseType: "json" });
  }


  getAllRiskBehavior(): Observable<any> {
    let url = this.END_POINT + "/1/100000"
    return this.http.get<any>(url, { responseType: "json" });
  }

  // updateOccupation(occupation: Occupation): Observable<Occupation> {
  //   var requestHeaders = new HttpHeaders();
  //   let url = this.END_POINT + "/" + occupation.id
  //   return this.http.put<Occupation>(url + "/", occupation, { headers: requestHeaders, responseType: "json" });
  // }

  deleteRiskBehavior(id: string): Observable<RiskBehavior> {
    let url = this.END_POINT + "/" + id
    return this.http.delete<RiskBehavior>(url, { responseType: "json" });
  }

}
