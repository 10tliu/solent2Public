/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.impl;

import solent.ac.uk.ood.examples.cardcheck.CalculateLunnDigit;
import solent.ac.uk.ood.examples.cardcheck.CardCompany;
import solent.ac.uk.ood.examples.cardcheck.CardValidationResult;
import solent.ac.uk.ood.examples.cardcheck.RegexCardValidator;
import solent.ac.uk.ood.examples.cardvalidator.model.CardOrganisation;
import solent.ac.uk.ood.examples.cardvalidator.model.CreditCard;
import solent.ac.uk.ood.examples.cardvalidator.model.CreditCardFactoryAndValidator;
import solent.ac.uk.ood.examples.cardvalidator.model.CvvAlgorythimStrategy;

/**
 *
 * @author cgallen
 */
public class CreditCardFactoryAndValidatorImpl implements CreditCardFactoryAndValidator {

    private CvvAlgorythimStrategy cvvAlgorythimStrategy = null;
    private String iin = null;

    // to be set before using class
    @Override
    public void setIssuerIdentificationNumber(String iin) {
        this.iin = iin;
    }

    @Override
    public void setCvvAlgorythim(CvvAlgorythimStrategy cvvAlgorythimStrategy) {
        this.cvvAlgorythimStrategy = cvvAlgorythimStrategy;
    }

    @Override
    public CreditCard createCreditCard(String individualAccountIdentifier, String name, String endDate, String issueNo) {
        CreditCard card = new CreditCard();

        // set simple values
        card.setName(name);
        card.setIssueNumber(issueNo);
        card.setEndDate(endDate);

        // create card number without the lunn check
        String noLunnCardNumber = iin + individualAccountIdentifier;

        // calculate lunn check
        String check = CalculateLunnDigit.calculateCheckDigit(noLunnCardNumber);

        // append lunn check digit
        String ccNumber = noLunnCardNumber + check;

        card.setCardnumber(ccNumber);

        // calculate and add cvv to final card number
        card = cvvAlgorythimStrategy.addCvv(card);

        return card;
    }

    @Override
    public boolean cvvIsValid(CreditCard card) {
        return cvvAlgorythimStrategy.checkCvv(card);
    }

    @Override
    public CardOrganisation getCardOrganisation(CreditCard card) {
        //TODO
        //assertEquals(result.getCardType(),CardCompany.VISA );
        String ccNumber = card.getCardnumber();
        CardValidationResult result = RegexCardValidator.isValid(ccNumber);
        if(result.getCardType().equals(CardCompany.VISA))return CardOrganisation.VISA
        //do this for all ca
    }

    @Override
    public boolean cardNumberLunnIsValid(CreditCard card) {
        //TODO
        String ccNumber = card.getCardnumber();
        CardValidationResult result = RegexCardValidator.isValid(ccNumber);
        return result.isValid();
    }

}
