import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from "@angular/common/http";
// import { Occupation } from 'src/app/_models/occupation.model';
import { ConfigInitService } from 'src/app/init/config-init.service';
import { SearchObject } from 'src/app/_interfaces/search-object';
import { PopulationGroup } from 'src/app/_models/population-group.model';

@Injectable({
  providedIn: 'root'
})

export class PopulationGroupService {

  constructor(private http: HttpClient, private configInitService: ConfigInitService) { }

  private readonly END_POINT = this.configInitService.apiBaseUrl + '/api/v1/populationgroup';

  // phan trang method post
  pagingPopulationGroups(searchObject: SearchObject): Observable<any> {
    const url = this.END_POINT + "/paging"
    return this.http.post<any>(url, searchObject,
      { responseType: "json" }
    );
  }

  getPopulationGroups(id: string): Observable<PopulationGroup> {
    let url = this.END_POINT + "/" + id
    return this.http.get<PopulationGroup>(url, { responseType: "json" });
  }

  saveOrUpdatePopulationGroups(populationGroups: PopulationGroup): Observable<PopulationGroup> {
    var requestHeaders = new HttpHeaders();
    let url = this.END_POINT + "/save"
    return this.http.post<PopulationGroup>(url, populationGroups, { headers: requestHeaders, responseType: "json" });
  }

  getAllPopulationGroup(): Observable<any> {
    let url = this.END_POINT
    return this.http.get<any>(url, { responseType: "json" });
  }

  // updateOccupation(occupation: Occupation): Observable<Occupation> {
  //   var requestHeaders = new HttpHeaders();
  //   let url = this.END_POINT + "/" + occupation.id
  //   return this.http.put<Occupation>(url + "/", occupation, { headers: requestHeaders, responseType: "json" });
  // }

  deletePopulationGroups(id: string): Observable<PopulationGroup> {
    let url = this.END_POINT + "/" + id
    return this.http.delete<PopulationGroup>(url, { responseType: "json" });
  }

}
