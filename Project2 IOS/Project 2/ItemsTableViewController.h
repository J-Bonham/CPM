//
//  ItemsTableViewController.h
//  Project 2
//
//  Created by Jeremiah Bonham on 3/10/15.
//  Copyright (c) 2015 Jeremiah.Bonham. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ItemsTableViewController : UITableViewController <UITableViewDataSource, UITableViewDelegate>
{
    long selectedObject;
}

@property (strong, nonatomic) NSArray *allItems;

@end
