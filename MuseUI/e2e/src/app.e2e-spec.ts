import { AppPage } from './app.po';
import { browser, by, element, protractor } from 'protractor';
import { async } from 'q';

describe('frontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MuseUI');
  });

  it('should be redirected to /login route on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {
    browser.element(by.id('email')).sendKeys('Super User');
    browser.element(by.id('name')).sendKeys('Super lastUser');
    browser.element(by.id('userId')).sendKeys('Super User12');
    browser.element(by.id('password')).sendKeys('Super Userpass');
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });
  

  it('should be able to login user and navigate to all jobs', () => {
    browser.element(by.id('userId')).sendKeys('Super User12');
    browser.element(by.id('password')).sendKeys('Super Userpass');
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/job/all');
  });

  

  it('should be able to add movie to watchlist', async() => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    browser.element(by.css('.addButton')).click();
    browser.driver.sleep(10000);
  });

  it('should be able to search job', () => {
    browser.element(by.css('.search-button')).click();
    expect(browser.getCurrentUrl()).toContain('/job/search');
    
  });
 
});
