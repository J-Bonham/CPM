//
//  EditItemViewController.m
//  Project 2
//
//  Created by Jeremiah Bonham on 3/12/15.
//  Copyright (c) 2015 Jeremiah.Bonham. All rights reserved.
//

#import "EditItemViewController.h"
#import <Parse/Parse.h>

@interface EditItemViewController ()

@end

@implementation EditItemViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.itemName.text = [self.selectedObject valueForKey:@"itemname"];
    self.ipAddress.text = [self.selectedObject valueForKey:@"ip"];
    //self.wireless.isOn = [self.selectedObject valueForKey:@"wireless"];
    
    
}


- (IBAction)onClick:(id)sender {
    
    if([_itemName.text length] == 0 || [_ipAddress.text length] == 0) {
        
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Unable to save item" message:@"Please enter a name and IP address" delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
        [alert show];
        
        [_itemName becomeFirstResponder];
        
    } else {
        
        PFObject *networkItem = [PFObject objectWithClassName:@"Items"];
        networkItem[@"itemname"] = _itemName.text;
        networkItem[@"ip"] = _ipAddress.text;
        networkItem[@"wireless"] = @(self.wireless.isOn);
        networkItem.ACL = [PFACL ACLWithUser:[PFUser currentUser]];
        [networkItem saveInBackground];
        [self dismissViewControllerAnimated:YES completion:nil];
        
    }
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)cancelClick:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

@end
