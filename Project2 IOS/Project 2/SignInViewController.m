//
//  SignInViewController.m
//  Project 2
//
//  Created by Jeremiah Bonham on 3/8/15.
//  Copyright (c) 2015 Jeremiah.Bonham. All rights reserved.
//

#import "SignInViewController.h"
#import <Parse/Parse.h>

@interface SignInViewController ()

@end

@implementation SignInViewController


- (void)viewDidLoad
{
    [super viewDidLoad];
    
    PFUser *currentUser = [PFUser currentUser];
    if(currentUser){
        [self performSegueWithIdentifier:@"SignInSegue" sender:self];
    }
}

- (void) viewDidAppear:(BOOL)animated{
    
    self.userNameInput.text = @"";
    self.passwordInput.text = @"";
    
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)signinButton {
    
    username = self.userNameInput.text;
    password = self.passwordInput.text;
    
    if (username.length == 0 || password.length == 0) {
        [[[UIAlertView alloc] initWithTitle:@"Login Error" message:@"You must enter both a username and password!" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil] show];
    } else {
        [PFUser logInWithUsernameInBackground:self.userNameInput.text password:self.passwordInput.text block:^(PFUser *user, NSError *error) {
            if (user) {
                [self performSegueWithIdentifier:@"SignInSegue" sender:self];
            } else {
                [[[UIAlertView alloc] initWithTitle:@"Login Error" message:@"Username and/or password is incorrect. Please try again!" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil] show];
            }
        }];
    }
}

@end
