import { ApiDashboardPage } from './app.po';

describe('api-dashboard App', () => {
  let page: ApiDashboardPage;

  beforeEach(() => {
    page = new ApiDashboardPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
