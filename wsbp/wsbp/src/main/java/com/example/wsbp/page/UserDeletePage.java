package com.example.wsbp.page;

import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.example.wsbp.service.IUserService;

@MountPath("UserDelete")
public class UserDeletePage extends WebPage {

    //IUserService を IoC/DI する
    @SpringBean
    private IUserService userService;

    public UserDeletePage() {
        var userNameModel = Model.of("");

        var toHome = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHome);


        Form<Void> userDeleteForm = new Form<>("userInfo") {
            @Override
            protected void onSubmit() {
                var userName = userNameModel.getObject();
                var msg = "送信データ："
                        + userName;
                System.out.println(msg);
                // IoC/DI した userService のメソッドを呼び出す
                userService.removeUser(userName);
                setResponsePage(new UserDeleteCompPage(userNameModel));
            }
        };
        add(userDeleteForm);

        var userNameField = new TextField<>("userName", userNameModel);
        userDeleteForm.add(userNameField);


    }



}
