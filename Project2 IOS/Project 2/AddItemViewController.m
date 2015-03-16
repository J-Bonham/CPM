//
//  AddItemViewController.m
//  Project 2
//
//  Created by Jeremiah Bonham on 3/10/15.
//  Copyright (c) 2015 Jeremiah.Bonham. All rights reserved.
//

#import "AddItemViewController.h"
#import <Parse/Parse.h>
#import "ItemsTableViewController.h"

@interface AddItemViewController ()

@end

@implementation AddItemViewController

@synthesize itemName;
@synthesize ipAddress;
@synthesize wireless;


- (void)viewDidLoad {
    [super viewDidLoad];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

- (IBAction)onClick:(id)sender {
    
    if([itemName.text length] == 0 || [ipAddress.text length] == 0) {
              
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Unable to save item" message:@"Please enter a name and IP address" delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
        [alert show];
           
    } else {
            
        PFObject *networkItem = [PFObject objectWithClassName:@"Items"];
            networkItem[@"itemname"] = itemName.text;
            networkItem[@"ip"] = ipAddress.text;
            networkItem[@"wireless"] = @(self.wireless.isOn);
              
            networkItem.ACL = [PFACL ACLWithUser:[PFUser currentUser]];
            [networkItem saveInBackground];
                
        [self dismissViewControllerAnimated:YES completion:nil];
    }
}
        

- (IBAction)cancelClick:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}


@end
