//
//  ItemsTableViewController.m
//  Project 2
//
//  Created by Jeremiah Bonham on 3/10/15.
//  Copyright (c) 2015 Jeremiah.Bonham. All rights reserved.
//

#import "ItemsTableViewController.h"
#import <Parse/Parse.h>
#import "CustomTableViewCell.h"
#import "EditItemViewController.h"

@interface ItemsTableViewController ()

@end

@implementation ItemsTableViewController

@synthesize allItems;

- (void)viewDidLoad {
    [super viewDidLoad];
    
    allItems = [[NSArray alloc] init];
    
    PFQuery *query = [PFQuery queryWithClassName:@"Items"];
    [query findObjectsInBackgroundWithBlock:^(NSArray *objects, NSError *error) {
        if(!error) {
            allItems = objects;
//            [self.tableView reloadData];
        } else {
            UIAlertView *message = [[UIAlertView alloc] initWithTitle:@"Error" message:[error userInfo][@"error"] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            [message show];
            
        }
    }];
}


- (void) viewDidAppear:(BOOL)animated {
    PFQuery *query = [PFQuery queryWithClassName:@"Items"];
    [query findObjectsInBackgroundWithBlock:^(NSArray *objects, NSError *error) {
        if(!error) {
            allItems = objects;
            [self.tableView reloadData];
        } else {
            UIAlertView *message = [[UIAlertView alloc] initWithTitle:@"Error" message:[error userInfo][@"error"] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            [message show];
        }
    }];
    [self.tableView reloadData];

}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    // Return the number of rows in the section.
    return allItems.count;
}


-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    selectedObject = indexPath.row;
    [self performSegueWithIdentifier:@"EditSegue" sender:self];
    
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    EditItemViewController *editItemVC = segue.destinationViewController;
    editItemVC.selectedObject = [allItems objectAtIndex:selectedObject];
    
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    CustomTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"cells" forIndexPath:indexPath];
    
    NSString *itemName = allItems[indexPath.row][@"itemname"];
    NSString *ipaddress = allItems[indexPath.row][@"ip"];
    BOOL wirelessBool = [allItems[indexPath.row][@"wireless"] boolValue];
    cell.itemCellText.text = itemName;
    cell.itemIpAddressText.text = ipaddress;
    
    if (wirelessBool) {
        cell.itemWirelessText.text = @"Wireless";
    } else {
        cell.itemWirelessText.text = @"Not Wireless";
    }
    
    return cell;
}


- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    return YES;
}


- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    
    if(editingStyle == UITableViewCellEditingStyleDelete){
        
        PFObject *item = [self.allItems objectAtIndex:indexPath.row];
        [item deleteInBackgroundWithBlock:^(BOOL succeeded, NSError *error) {
            PFQuery *query = [PFQuery queryWithClassName:@"Items"];
            [query findObjectsInBackgroundWithBlock:^(NSArray *objects, NSError *error) {
                if(!error) {
                    allItems = objects;
                    [self.tableView reloadData];
                }
            }];
    
        }];

    }
}

- (IBAction)signOutClick {
    [PFUser logOut];
    [self dismissViewControllerAnimated:YES completion:nil];
    [self.navigationController popToRootViewControllerAnimated:YES];
    
    NSLog(@"User Clicked Sign Out");
}

@end
