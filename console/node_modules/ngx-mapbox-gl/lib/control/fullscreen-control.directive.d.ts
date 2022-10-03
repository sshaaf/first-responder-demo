import { AfterContentInit } from '@angular/core';
import { MapService } from '../map/map.service';
import { ControlComponent } from './control.component';
export declare class FullscreenControlDirective implements AfterContentInit {
    private MapService;
    private ControlComponent;
    container?: HTMLElement;
    constructor(MapService: MapService, ControlComponent: ControlComponent);
    ngAfterContentInit(): void;
}
