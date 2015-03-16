//
//  EditItemViewController.h
//  Project 2
//
//  Created by Jeremiah Bonham on 3/12/15.
//  Copyright (c) 2015 Jeremiah.Bonham. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Parse/Parse.h>


@interface EditItemViewController : UIViewController

@property (weak, nonatomic) IBOutlet UITextField *itemName;
@property (weak, nonatomic) IBOutlet UITextField *ipAddress;
@property (weak, nonatomic) IBOutlet UISwitch *wireless;

@property (strong, nonatomic) PFObject *selectedObject;


@end
