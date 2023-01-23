import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Ethnics } from 'src/app/_models/ethnics.model';
import { ConfigInitService } from 'src/app/init/config-init.service';
import { SearchObject } from 'src/app/_interfaces/search-object';

@Injectable({
  providedIn: 'root'
})

export class EthnicsService {

  constructor(private http: HttpClient, private configInitService: ConfigInitService) { }

  private readonly END_POINT = this.configInitService.apiBaseUrl + '/api/v1/ethnicsext';

  // phan trang method post
  pagingEthnics(searchObject: SearchObject): Observable<any> {
    const url = this.END_POINT + "/paging"
    return this.http.post<any>(url, searchObject,
      { responseType: "json" }
    );
  }
  getEthnics(id: string): Observable<Ethnics> {
    let url = this.END_POINT + "/" + id
    return this.http.get<Ethnics>(url, { responseType: "json" });
  }
  saveOrUpdateEthnics(ethnics: Ethnics): Observable<Ethnics> {
    var requestHeaders = new HttpHeaders();
    let url = this.END_POINT + "/save";
    return this.http.post<Ethnics>(url, ethnics, { headers: requestHeaders, responseType: "json" });
  }
  deleteEthnics(id: string): Observable<Ethnics> {
    let url = this.END_POINT + "/" + id
    return this.http.delete<Ethnics>(url, { responseType: "json" });
  }
  ///getAll
  getAll():Observable<any> {
    let url = this.END_POINT + "/getAll"
    return this.http.get<any>(url, { responseType: "json" });
  }
}
