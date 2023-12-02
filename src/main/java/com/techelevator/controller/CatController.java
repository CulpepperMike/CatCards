package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/cards")
@CrossOrigin
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

   @RequestMapping(method = RequestMethod.GET)
    public List<CatCard> list() {
        return catCardDao.getCatCards();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CatCard get(@PathVariable int id) {
        return catCardDao.getCatCardById(id);
    }

    @RequestMapping(path = "/random", method = RequestMethod.GET)
    public CatCard random() {
        CatFact catFact = catFactService.getFact();
        CatPic catPic = catPicService.getPic();
        CatCard catCard = new CatCard();
        catCard.setCatFact(catFact.getText());
        catCard.setImgUrl(catPic.getFile());
        return catCard;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public CatCard create(@Valid @RequestBody CatCard catCard) {
        return catCardDao.createCatCard(catCard);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public CatCard update(@Valid @PathVariable int id, @RequestBody CatCard catCard) {
        catCard.setCatCardId(id);
        try {
            return catCardDao.updateCatCard(catCard);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        catCardDao.deleteCatCardById(id);
    }

}
