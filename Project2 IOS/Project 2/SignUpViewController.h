//
//  SignUpViewController.h
//  Project 2
//
//  Created by Jeremiah Bonham on 3/8/15.
//  Copyright (c) 2015 Jeremiah.Bonham. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface SignUpViewController : UIViewController <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UITextField *userNameInput;
@property (weak, nonatomic) IBOutlet UITextField *passwordInput;

@end
