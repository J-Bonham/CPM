//
//  SignUpViewController.m
//  Project 2
//
//  Created by Jeremiah Bonham on 3/8/15.
//  Copyright (c) 2015 Jeremiah.Bonham. All rights reserved.
//

#import "SignUpViewController.h"
#import <Parse/Parse.h>

@interface SignUpViewController ()

@end

@implementation SignUpViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)registerClicked:(id)sender {

    PFUser *user = [PFUser user];
    user.username = self.userNameInput.text;
    user.password = self.passwordInput.text;
    
    if (self.userNameInput.text.length == 0 || self.passwordInput.text.length ==0) {
        [[[UIAlertView alloc] initWithTitle:@"Problem Creating Account" message:@"Please enter a User Name and Password" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil] show];
        
    } else {
        
        [user signUpInBackgroundWithBlock:^(BOOL succeeded, NSError *error) {
            if (error) {
                [[[UIAlertView alloc] initWithTitle:@"Problem Creating Account" message:@"Username already exists, please choose a different Username" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil] show];
            
            } else {
                
                [self dismissViewControllerAnimated:YES completion:nil];
                
            }
        }];
    }
}

- (IBAction)cancelClick:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

@end