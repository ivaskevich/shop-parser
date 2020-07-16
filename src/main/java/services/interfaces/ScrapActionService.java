package services.interfaces;

import entities.SiteRepresent;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ScrapActionService {
    void performScraping(List<SiteRepresent> siteRepresents);
}
