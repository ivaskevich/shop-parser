package services.interfaces;

import entities.SiteRepresent;

public interface ScrapActionService {
    void performScraping();

    void addSiteRepresent(SiteRepresent siteRepresent);
}
