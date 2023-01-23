import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from 'rxjs';
import { ConfigInitService } from 'src/app/init/config-init.service';
import { Country } from 'src/app/_models/country.model';
import { SearchObject } from 'src/app/_interfaces/search-object';

@Injectable({
    providedIn: 'root'
})
export class CountryService {
    constructor(private http: HttpClient, private configInitService: ConfigInitService) { }
    private readonly END_POINT = this.configInitService.apiBaseUrl + '/api/v1/countryext';
    
    pagingCountrys(searchObject: SearchObject): Observable<any> {
        const url = this.END_POINT + "/paging"
        return this.http.post<any>(url, searchObject,
          { responseType: "json" }
        );
    }

    getCountry(id: string): Observable<Country> {
        let url = this.END_POINT + "/" + id
        return this.http.get<Country>(url, { responseType: "json" });
    }

    saveOrUpdateCountry(country: Country): Observable<Country> {
        var requestHeaders = new HttpHeaders();
        let url = this.END_POINT + "/save";
        return this.http.post<Country>(url, country, { headers: requestHeaders, responseType: "json" });
    }

    deleteCountry(id : string): Observable<Country> {
        let url = this.END_POINT + "/" + id
        return this.http.delete<Country>(url, {responseType: "json"});
    }
}